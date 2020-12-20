//
//  Checkpoint.swift
//  runtracker
//
//  Created by wheejuni on 2020/11/12.
//  Copyright © 2020 wheejuni. All rights reserved.
//

import Foundation

struct Checkpoint {
    
    var latitude = 0.0
    var longitude = 0.0
    
    init(latitude: Double, longitude: Double) {
        self.latitude = latitude
        self.longitude = longitude
    }
}
