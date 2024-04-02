
import Foundation
import SwiftUI
import ComposeApp

struct FeedTab : View {
    var images: [ImageResultViewEntity] = []

    var body: some View {
        let columns = [
                    GridItem(.flexible(minimum: 128, maximum: 256), spacing: 16),
                    GridItem(.flexible(minimum: 128, maximum: 256), spacing: 16)
                ]
        ScrollView{
                 LazyVGrid(columns: columns, spacing: 16){

                 }.padding(.horizontal, 16)
             }
    }
}
