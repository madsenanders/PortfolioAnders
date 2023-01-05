//
//  ModelTrorJeg.swift
//  ObligatoriskOpgaveAndersMadsen
//
//  Created by dmu mac 21 on 10/11/2022.
//

import Foundation
import MapKit

struct EarthquakeResponse: Codable {
    let features: [Earthquake]
}

struct Earthquake: Identifiable, Codable {
    var properties: Properties
    var id: String
    var geometry: Geometry
    
    var location: CLLocationCoordinate2D {
        CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
}
    var longitude: Double {geometry.coordinates[0]}
    var latitude: Double {geometry.coordinates[1]}

    struct Properties: Codable {
        var mag: Float
        var place: String
        var time: Date // todo
        var updated: Date
    }
    struct Geometry: Codable {
            let coordinates: [Double]
        }
    
    func getRegion() -> MKCoordinateRegion{
        let region = MKCoordinateRegion(
            center: CLLocationCoordinate2D(
                latitude: latitude,
                longitude: longitude),
            span: MKCoordinateSpan(
                latitudeDelta: 0.5,
                longitudeDelta: 0.5))
        return region
    }}




