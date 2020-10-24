/*
 * Copyright 2019 Nikolas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fractals;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Nikolas
 */
public class FracKey implements KeyListener{

    Mandelbrot set;
    public FracKey(Mandelbrot set){
        super();
        this.set = set;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("test");
        if(e.getKeyChar() == 'i'){
            set.maxIterations+= 100;
            System.out.println(set.maxIterations);
        }else if(e.getKeyChar() == 'k'){
            set.maxIterations-= 100;
            System.out.println(set.maxIterations);
        }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
