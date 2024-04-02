import Foundation
import SwiftUI
import ComposeApp

struct SearchResultsView : View {
    var results: [ImageResultViewEntity]
    
    let columns = [
         GridItem(.adaptive(minimum: 80))
     ]
    
    var body: some View {
        GeometryReader { geometry in
            ScrollView {
                LazyVStack {
                    LazyVGrid(columns: columns, spacing: 20) {
                        ForEach(results.indices, id: \.self) { index in
                            ImageView(imageUrl: results[index].photo.url)
                                .frame(width: 100, height: 100)
                                .cornerRadius(8)
                        }
                    }
                    .padding(.horizontal)
                    
                }
            }.frame(maxHeight: geometry.size.height * 0.9) //
        }
    }
}
