//
//  SearchViewModel.swift
//  iosApp
//
//  Created by Abass Bayo on 4/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import ComposeApp

class SearchViewModel : ObservableObject {
    @Published var query: String = ""
    @Published var results: [ImageResultViewEntity]
    
    private let getSearchResultViewEntities = GetSearchResultViewEntities(viewMapper: SearchViewMapper(), getSearchScreenUi: GetSearchScreenUi(appDataSource: AppRepository.init()))
    
    
    
    init() {
        self.results = []
    }
    
    func loadResults(query: String) {
        getSearchResultViewEntities.invoke(query: query) { data, error in
            DispatchQueue.main.async {
                self.results = data ?? []
            }
        }
        
    }
}
