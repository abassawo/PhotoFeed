//
//  SearchScreen.swift
//  iosApp
//
//  Created by Abass Bayo on 4/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct SearchTab : View {
    @State private var query: String = ""

    var body: some View {

        TextField("Search an image", text: $query)
    }
}
