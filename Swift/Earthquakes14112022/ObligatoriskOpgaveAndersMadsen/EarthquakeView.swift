//
//  ContentView.swift
//  ObligatoriskOpgaveAndersMadsen
//
//  Created by dmu mac 21 on 10/11/2022.
//

import SwiftUI
import MapKit



   

struct ContentView: View {
        @EnvironmentObject var earthquakeVM: EarthquakeViewModel
        private var earthquakes: [Earthquake] {
            earthquakeVM.earthquakesList.sorted{$0.properties.mag > $1.properties.mag
            }
        }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(earthquakes) { earthquake in
                    EarthquakeList(earthquake: earthquake)
                        .navigationTitle("Earthquakes")
                }
            }
        }
    }
    
}

struct EarthquakeList: View {
    @State var earthquake: Earthquake

    var body: some View {
        NavigationLink(destination: EarthquakeMap(
                earthquakeMarker: $earthquake,
                region: earthquake.getRegion())) {
            HStack() {
                Image(systemName: "globe")
                Text("\(String(format: "%g", earthquake.properties.mag))")
                VStack(alignment: .leading) {
                    Text("\(earthquake.properties.time.formatted())")
                    Text("\(earthquake.properties.place)")
                }
            }
        }
        .background(.yellow)
        .cornerRadius(15)
    }
}

struct EarthquakeDetail: View {
    @Binding var earthquake: Earthquake
    
    var body: some View {
        VStack(spacing: 5) {
            HStack {
                Text("ID")
                Text(earthquake.id)
            }
            HStack {
                Text("Place")
                Text(earthquake.properties.place)
            }
            HStack {
                Text("Time")
                Text(earthquake.properties.time.formatted())
           }
            HStack {
                Text("Updated")
                Text(earthquake.properties.updated.formatted())             }
            HStack {
                Text("Magnitude")
                Text(String(format: "%g", earthquake.properties.mag))
        }        }

        
    }
    }

struct EarthquakeMap: View {
    @Binding var earthquakeMarker: Earthquake
    @State var region: MKCoordinateRegion
    
    var body: some View {
        ZStack {
            Color.yellow.edgesIgnoringSafeArea(.all);       Map(coordinateRegion: $region, annotationItems: [earthquakeMarker]) { earthquake in
                MapMarker(coordinate: earthquake.location, tint: .red)
                           }
            .navigationTitle(earthquakeMarker.properties.place)
            .toolbar {
                NavigationLink(destination: EarthquakeDetail(earthquake: $earthquakeMarker)) {
                        Text("DetailView")
                }
            }.navigationBarTitleDisplayMode(.inline)
        }
    }
}



struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView().environmentObject(EarthquakeViewModel())
    }
}



