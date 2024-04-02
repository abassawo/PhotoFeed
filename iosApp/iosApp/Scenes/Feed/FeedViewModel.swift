import Foundation
import Combine
import ComposeApp

class FeedViewModel : ObservableObject {
    
    private let recordSearchHistory = RecordSearchHistory(sharedPreferences: PreferenceUtil())
    
    private let getSearchResultViewEntities = GetSearchResultViewEntities(viewMapper: SearchViewMapper(), getSearchScreenUi: GetSearchScreenUi(appDataSource: AppRepository.init()))
}
