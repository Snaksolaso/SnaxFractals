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

import java.awt.*;

/**
 *
 * @author Snaxolas
 */
public class RGB {
    int r;
    int g;
    int b;
    public RGB(int r, int g,int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public RGB(Color color){
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
    }
    public int getR(){
        return r;
    }
    public int getG(){
        return g;
    }
    public int getB(){
        return b;
    }

    public boolean isBlack(){
        return (r == 0 && g == 0 && b == 0);
    }
    
}
