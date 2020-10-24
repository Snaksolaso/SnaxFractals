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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Nikolas
 */
public class FracMouse implements MouseListener{
    
    Mandelbrot set;

    public FracMouse(Mandelbrot set){
        super();
        this.set = set;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1){
            set.zoom(e.getX(),e.getY(), (double)1/8);
        }else if(e.getButton() == 2){
            set.maxIterations += 100;
            System.out.println(set.maxIterations);
        }else if(e.getButton() == 3){
            set.maxIterations -= 100;
            System.out.println(set.maxIterations);
        }
        
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }

    
    @Override
    public void mouseExited(MouseEvent e) {
    }

    
    
}
