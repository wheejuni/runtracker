//
//  ExerciseMapView.swift
//  runtracker
//
//  Created by wheejuni on 2020/11/12.
//  Copyright © 2020 wheejuni. All rights reserved.
//

import MapKit
import SwiftUI

struct ExerciseMapView: UIViewRepresentable {
    
    typealias UIViewType = MKMapView
    
    func makeUIView(context: Context) -> MKMapView {
        return MKMapView(frame: UIScreen.main.bounds)
    }
    
    func updateUIView(_ uiView: MKMapView, context: Context) {
    }
}

struct ExerciseMapView_Previews: PreviewProvider {
    static var previews: some View {
        ExerciseMapView()
    }
}
