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
    
    FractalSet set1;
    FractalSet set2;

    public FracMouse(FractalSet set1, FractalSet set2){
        super();
        this.set1 = set1;
        this.set2 = set2;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1){
            set1.zoom(e.getX(),e.getY(), (double)1/8);
            set2.zoom(e.getX(),e.getY(), (double)1/8);
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
