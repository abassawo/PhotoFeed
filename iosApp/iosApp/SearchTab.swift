
import Foundation
import SwiftUI
import ComposeApp

struct SearchTab : View {
    @State private var query: String = ""
//    @ObservedObject var images: [ImageResultViewEntity]
    
    @ObservedObject var searchViewModel = SearchViewModel()
    
    var body: some View {
        NavigationView {
        VStack {
            HStack {
                TextField("Search an image", text: $query) {
                    
                }
                Button(action: {
                    searchViewModel.loadResults(query: query)
//                    images = i
                }, label: {
                    Text("Search")
                })
            }.padding(16)
            ScrollView {
                LazyVStack {
                    ForEach(searchViewModel.results.indices, id: \.self) { index in
                        ImageView(imageUrl: searchViewModel.results[index].photo.url)
                            .frame(width: 100, height: 100)
                            .cornerRadius(8)
                    }
                }
            }
        }
        }
                    .navigationBarTitle("Photo Feed")
    }
}

struct ImageView: View {
    let imageUrl: String
    
    var body: some View {
        if let url = URL(string: imageUrl) {
            AsyncImage(url: url)
                .frame(width: 100, height: 100) // Adjust size as needed
                .aspectRatio(contentMode: .fit)
                .cornerRadius(8)
        } else {
            Text("Invalid URL")
        }
    }
}

extension ImageResultViewEntity : Identifiable {
    
}

extension ImageResultViewEntity : ObservableObject {
    
}
