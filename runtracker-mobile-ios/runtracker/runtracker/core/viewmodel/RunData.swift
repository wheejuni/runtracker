//
//  RunData.swift
//  runtracker
//
//  Created by wheejuni on 2020/11/12.
//  Copyright © 2020 wheejuni. All rights reserved.
//

import Foundation

class Rundata: ObservableObject {
    
    var runId: UInt32
    @Published var checkpoints: [Checkpoint]
    
    init(runId: UInt32) {
        self.runId = runId
        self.checkpoints = []
    }
    
    func addCheckPoint(checkpoint: Checkpoint) -> Rundata {
        checkpoints.append(checkpoint)
        return self
    }
    
}
