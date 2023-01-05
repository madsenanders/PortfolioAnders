//
//  ObligatoriskOpgaveAndersMadsenApp.swift
//  ObligatoriskOpgaveAndersMadsen
//
//  Created by dmu mac 21 on 10/11/2022.
//

import SwiftUI

@main
struct MainApp: App {
    @StateObject var EarthquakeVM = EarthquakeViewModel()
    
    var body: some Scene {
        WindowGroup {
            ContentView().environmentObject(EarthquakeVM)
        }
    }
}
