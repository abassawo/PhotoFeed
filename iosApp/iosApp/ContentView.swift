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
                    }
                FeedTab()
                    .tabItem {
                        Label("History", systemImage: "tray.and.arrow.down.fill")
                    }
            }
            .navigationTitle("Photo Feed")
            .navigationBarTitleDisplayMode(.inline)
            
        }
    }
}

@ToolbarContentBuilder
private func mainToolbar() -> some ToolbarContent {
    ToolbarItem(placement: .principal) {
        Text("Photo Feed")
            .font(.title3)
            .frame(maxHeight: 100)
    }
}
        
