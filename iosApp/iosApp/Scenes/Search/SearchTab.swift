
import Foundation
import SwiftUI
import ComposeApp

struct SearchTab : View {
    @State private var query: String = ""
    @ObservedObject var searchViewModel = SearchViewModel()
    
    var body: some View {
            VStack {
                HStack {
                    TextField("Search an image", text: $query) {
                        
                    }
                    Button(action: {
                        searchViewModel.loadResults(query: query)
                    }, label: {
                        Text("Search")
                    })
                }.padding(16)
                SearchResultsView(results: searchViewModel.results)
            }
    }
}
