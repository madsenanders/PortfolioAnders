//
//  SwiftUIView.swift
//  ObligatoriskOpgaveAndersMadsen
//
//  Created by dmu mac 21 on 10/11/2022.
//

import SwiftUI
import Foundation

class EarthquakeViewModel: ObservableObject {
    
    @Published var earthquakesList = [Earthquake]()
    

    func fetchData() async throws -> Data? {
        let earthquakeURL = URL(string: "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson")!
        let session = URLSession.shared
        let (data, response) = try await session.data(from: earthquakeURL)
        let httpResponse = response as! HTTPURLResponse
        if httpResponse.statusCode == 200 {
            return data
        }
        else {
            return nil
        }
        
    }
    
    init(){
        Task {
            let data = try! await fetchData()
            if let data = data {
                // print(String(data: data, encoding: .utf8))
                let decoder = JSONDecoder()
                let response = try! decoder.decode(EarthquakeResponse.self, from: data)
                earthquakesList = response.features
            }
        }
        
    }
    
    enum EarthquakeSpan: String {
        case Hour =
                "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson"
        case Day =
                "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson"
        case Week =
                "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson"
        case Month =
                "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson"
    }
    
}
