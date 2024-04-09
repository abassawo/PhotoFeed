import Foundation
import Combine
import ComposeApp

enum SearchViewState {
    struct Content {
        let query: String
        let results: [ImageResultViewEntity]
        var isSearchActive: Bool = false
    }
    
    case loading
    case content(Content)
    case error(String)
}

class SearchViewModel : ObservableObject {
    @Published var query: String = ""
    @Published var viewState: SearchViewState = .content(SearchViewState.Content(query: "", results: [], isSearchActive: false))
    private let recordSearchHistory = RecordSearchHistory(sharedPreferences: PreferenceUtil())
   
    private let getSearchResultViewEntities = GetSearchResultViewEntities(viewMapper: SearchViewMapper(), getSearchScreenUi: GetSearchScreenUi(appDataSource: AppRepository.init()))
    
    func loadResults(query: String) {
        self.viewState = SearchViewState.loading
        getSearchResultViewEntities.invoke(query: query) { data, error in
            DispatchQueue.main.async {
                self.viewState = .content(SearchViewState.Content(query: query, results: data ?? [], isSearchActive: true))
            }
        }
        recordSearchHistory.invoke(query: query)
    }
}
