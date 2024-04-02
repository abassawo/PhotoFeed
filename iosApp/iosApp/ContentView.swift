import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {

    var body: some View {
        TabScreen()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}

struct TabScreen : View {
    @ObservedObject private var viewModel: SearchViewModel

     init() {
        KoinModuleKt.doInitKoin()
        viewModel = SearchViewModel.init()
     }
    
    var body: some View {
        NavigationView {
            
            TabView {
                SearchTab()
                    .tabItem {
                        Label("Search", systemImage: "magnifyingglass")
                    }.navigationTitle("Photo Feed")
                FeedTab()
                    .tabItem {
                        Label("History", systemImage: "tray.and.arrow.down.fill")
                    }
                    .navigationTitle("Photo Feed")
            }
        }
        .navigationTitle("Photo Feed")
        }
   
}

