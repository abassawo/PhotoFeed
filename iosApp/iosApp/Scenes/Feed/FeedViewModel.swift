import Foundation
import Combine
import ComposeApp

class FeedViewModel : ObservableObject {
    @Published var recentSearches: [String] = []
    @Published var results: Dictionary<String, [ImageResultViewEntity]> = [:]
    private let recordSearchHistory = RecordSearchHistory(sharedPreferences: PreferenceUtil())
    
    private let getSearchResultViewEntities = GetSearchResultViewEntities(viewMapper: SearchViewMapper(), getSearchScreenUi: GetSearchScreenUi(appDataSource: AppRepository.init()))
    private var isFirstRun: Bool = true
    
    init() {
        recentSearches = Array(recordSearchHistory.getHistory())
    }
    
    func loadResults(queries: [String]) {
        if(isFirstRun) {
            isFirstRun = false
            queries.forEach { query in
                getSearchResultViewEntities.invoke(query: query) { data, error in
                    DispatchQueue.main.async {
                        if(error != nil) {
                            print("Error " + error!.localizedDescription)
                        } else {
                            self.results[query] = data
                            self.results = self.results
                        }
                    }
                }
            }
    
        }
    }
}
