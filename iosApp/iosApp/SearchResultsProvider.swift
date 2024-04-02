import Foundation
import ComposeApp
import Combine

class SearchResultProvider: ObservableObject {
    
    @Published private(set) var results: [ImageResultViewEntity]?
    
    init(initialValue: [ImageResultViewEntity] = []) {
        self.results = initialValue
    }
    
    func fetch() {
        //.. fetch pricing from a service
    }
}
