//
//  FeedTab.swift
//  iosApp
//
//  Created by Abass Bayo on 4/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct FeedTab : View {
   

    var body: some View {

        VStack(
               alignment: .leading,
               spacing: 10
           ) {
               ForEach(
                   1...10,
                   id: \.self
               ) {
                   Text("Item \($0)")
               }
           }
    }
}
