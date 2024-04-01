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
    @Published var results: [ImageResultViewEntity]
    private let appDataSource = AppRepository.init()
    
    
    
    init() {
        self.results = []
//        createObservable()
    
        
    }
    
    private func loadResults(query: String) {
        appDataSource.getImages(query: query) { data, error in
            print("Result")
            self.results = self.makeViewEntities(rawResults: data ?? [])
        }
        
    }
    
    private func makeViewEntities(rawResults: [RawPhotoItem]) -> [ImageResultViewEntity] {
        return []
    }
}
