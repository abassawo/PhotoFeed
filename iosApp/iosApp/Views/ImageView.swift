import Foundation
import SwiftUI

struct ImageView: View {
    let imageUrl: String
    
    var body: some View {
        if let url = URL(string: imageUrl) {
            AsyncImage(url: url)
                .frame(width: 100, height: 100)
                .aspectRatio(contentMode: .fit)
                .cornerRadius(8)
        } else {
            Text("Invalid URL")
        }
    }
}
