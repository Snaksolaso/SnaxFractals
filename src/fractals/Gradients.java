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

import java.awt.Color;

/**
 *
 * @author Snaxolas
 */
public class Gradients {
    public Gradients(){
    }
    public int[] rainbow(int startAngle){
        RGB[] gradient = new RGB[510];
        int count = 0;
        for(int i = startAngle; i < 1530 + startAngle; i+=3){
            int sextant = (int) Math.floor((((double)i/(double)(1530))*6)%6);
            int val = i % 255;
            switch(sextant){
                case 0:
                    gradient[count] = new RGB(0,val,255);
                    break;
                case 1:
                    gradient[count] = new RGB(0,255,255 - val);
                    break;
                case 2:
                    gradient[count] = new RGB(val,255,0);
                    break;
                case 3:
                    gradient[count] = new RGB(255,255 - val,0);
                    break;
                case 4:
                    gradient[count] = new RGB(255,0,val);
                    break;
                case 5:
                    gradient[count] = new RGB(255 - val,0,255);
                    break;
            }
            count++;

        }
        
        int[] intRGB = new int[gradient.length];
        for(int i = 0; i < gradient.length; i++){
            intRGB[i] = new Color(gradient[i].r, gradient[i].g, gradient[i].b).getRGB();
        }
        return intRGB;
    }
    
}
