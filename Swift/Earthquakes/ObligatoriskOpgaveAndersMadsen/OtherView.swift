//
//  OtherView.swift
//  ObligatoriskOpgaveAndersMadsen
//
//  Created by dmu mac 21 on 13/11/2022.
//

import SwiftUI

struct OtherView: View {
    @EnvironmentObject var EarthquakeVM: EarthquakeViewModel
    @State var Earthquake: String
    var body: some View {
        ZStack {
        Color.red
            TextField(Earthquake, text: $Earthquake)
        .font(.largeTitle)
            
        }
            .ignoresSafeArea()
    }
}

struct OtherView_Previews: PreviewProvider {
    static let earthquakeVM = EarthquakeViewModel()
    static var previews: some View {
        OtherView(earthquakeVM: earthquakeVM)
    }
}
