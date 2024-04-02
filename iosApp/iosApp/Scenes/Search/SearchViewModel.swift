import Foundation
import Combine
import ComposeApp

class SearchViewModel : ObservableObject {
    @Published var query: String = ""
    @Published var results: [ImageResultViewEntity] = []
    private let recordSearchHistory = RecordSearchHistory(sharedPreferences: PreferenceUtil())
   
    private let getSearchResultViewEntities = GetSearchResultViewEntities(viewMapper: SearchViewMapper(), getSearchScreenUi: GetSearchScreenUi(appDataSource: AppRepository.init()))
    
    func loadResults(query: String) {
        getSearchResultViewEntities.invoke(query: query) { data, error in
            DispatchQueue.main.async {
                self.results = data ?? []
            }
        }
        recordSearchHistory.invoke(query: query)
    }
}
