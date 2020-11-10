//
//  ContentView.swift
//  runtracker
//
//  Created by wheejuni on 2020/11/08.
//  Copyright © 2020 wheejuni. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationView{
            Form {
                Text("bye World")
            }.navigationBarTitle("hellow")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
