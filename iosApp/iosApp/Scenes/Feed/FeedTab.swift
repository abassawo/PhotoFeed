
import Foundation
import SwiftUI
import ComposeApp

struct FeedTab : View {
    var images: [ImageResultViewEntity] = []
    @ObservedObject var viewModel = FeedViewModel()
    
    var body: some View {
        let columns = [
                    GridItem(.flexible(minimum: 128, maximum: 256), spacing: 16),
                    GridItem(.flexible(minimum: 128, maximum: 256), spacing: 16)
                ]
        ScrollView{
            let recentSearches = viewModel.recentSearches
            
            if(recentSearches.isEmpty) {
                Text("No recent searches")
            } else {
                LazyVStack {
                    ForEach(recentSearches.indices, id: \.self) { index in
                        VStack {
                            Text(recentSearches[index])
                                .onAppear {
                                    viewModel.loadResults(queries: recentSearches)
                                }
                            
                            ScrollView(.horizontal) {
                                LazyHStack {
                                    ForEach(viewModel.results[recentSearches[index]] ?? [], id: \.id) { item in
                                          // content
                                        ImageView(imageUrl: item.photo.url)
                                            .frame(width: 100, height: 100)
                                            .cornerRadius(8)
                                     }
                                     .listStyle(.plain)
                                }
                            }
                        
                        }
                        
                    }
                }.padding(.horizontal, 16)
            }
        }
    }
}

struct FeedContentView : View {
    let viewEntities: [FeedScreenContract.ViewEntity] = []
    
    var body: some View {
        VStack {
            
        }.onAppear {
            
        }
    }
}
