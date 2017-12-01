/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicompilador;

/**
 *
 * @author Ivan
 */
import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class genCodigo {
    
    public void guardarIno(String codigo){
        try{
            File archivo=new File("C:/Users/Ivan/Desktop/Ejemplo/Ejemplo.ino");
            if (archivo.exists()) {
                archivo.delete();
                System.out.println("Archivo Eliminado");
            }
                //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
                FileWriter escribir=new FileWriter(archivo,true);
                //Escribimos en el archivo con el metodo write
                escribir.write(codigo);
                //Cerramos la conexion
                escribir.close();
        }
            //Si existe un problema al escribir cae aqui
        catch(Exception e)
        {
            System.out.println("Error al escribir");
        }   
    }
    
    
    public String preparar(String funcions){
        String[] f = funcions.split("}");
        String fun = f[1];
        String funciones = fun.substring(3,fun.length()).trim();
        //--------------------------Hasta aqui esta sin inicio y fin----------------
        String[] funtions = funciones.split(";");
        for(int j=0;j<funtions.length;j++){
            String parametro = funtions[j].split("\\(")[0].trim();
            System.out.println(parametro);
            if(parametro.equals("escribir")){
                String cad = funtions[j].split("\\(")[1].trim();
                System.out.println(cad);
                String cad2 = cad.substring(1,cad.length()-2);
                System.out.println(cad2);
                String cad3="";
                for(int m=0;m<cad2.length();m++){
                    cad3=cad3+cad2.charAt(m)+"(4);";
                }
                funtions[j] = cad3.substring(0,cad3.length()-1);
            }
            if(parametro.equals("color")){
                funtions[j] = "";
            }
        }
        String funci = "";
        for(int k=0;k<funtions.length;k++){
            if(!funtions[k].isEmpty()){
                funci=funci+funtions[k].trim()+";\n";
            }
        }
        return funci;
    }
    
    public String generar (String funciones){
        
        String code="// \n" +
    "\n" +
    "#include <Servo.h>\n" +
    "//codigo de prueba\n" +
    "\n" +
    "\n" +
    "// setup servo\n" +
    "int servoPin = 8;\n" +
    "int PEN_DOWN = 20; // angle of servo when pen is down\n" +
    "int PEN_UP = 100;   // angle of servo when pen is up\n" +
    "Servo penServo;\n" +
    "\n" +
    "float wheel_dia=60; //    # mm (increase = spiral out)\n" +
    "float wheel_base=112; //    # mm (increase = spiral in, ccw) \n" +
    "int steps_rev=128; //        # 512 for 64x gearbox, 128 for 16x gearbox\n" +
    "int delay_time=4; //         # time between steps in ms\n" +
    "\n" +
    "// Stepper sequence org->pink->blue->yel\n" +
    "int L_stepper_pins[] = {12, 10, 9, 11};\n" +
    "int R_stepper_pins[] = {4, 6, 7, 5};\n" +
    "\n" +
    "int fwd_mask[][4] =  {{1, 0, 1, 0},\n" +
    "                      {0, 1, 1, 0},\n" +
    "                      {0, 1, 0, 1},\n" +
    "                      {1, 0, 0, 1}};\n" +
    "\n" +
    "int rev_mask[][4] =  {{1, 0, 0, 1},\n" +
    "                      {0, 1, 0, 1},\n" +
    "                      {0, 1, 1, 0},\n" +
    "                      {1, 0, 1, 0}};\n" +
    "\n" +
    "\n" +
    "void setup() {\n" +
    "  randomSeed(analogRead(1)); \n" +
    "  Serial.begin(9600);\n" +
    "  for(int pin=0; pin<4; pin++){\n" +
    "    pinMode(L_stepper_pins[pin], OUTPUT);\n" +
    "    digitalWrite(L_stepper_pins[pin], LOW);\n" +
    "    pinMode(R_stepper_pins[pin], OUTPUT);\n" +
    "    digitalWrite(R_stepper_pins[pin], LOW);\n" +
    "  }\n" +
    "  penServo.attach(servoPin);\n" +
    "  Serial.println(\"setup\");\n" +
    "\n" +
    "  penup();\n" +
    "  \n" +
    "  delay(1000);\n" +
    "}\n" +
    "\n" +
    "\n" +
    "\n" +
    "\n" +
    "\n" +
    "void loop(){ // draw a calibration box 4 times\n" +
    "  \n" +
    "  //-------------------------------------\n" +
    "\n" +
    "  "+preparar(funciones)+"\n" +
    "  \n" +
    "  //------------------------------------------\n" +
    "  done();      // releases stepper motor\n" +
    "  while(1);    // wait for reset\n" +
    "}\n" +

    "int step(float distance){\n" +
    "  int steps = distance * steps_rev / (wheel_dia * 3.1412); //24.61\n" +
    "  /*\n" +
    "  Serial.print(distance);\n" +
    "  Serial.print(\" \");\n" +
    "  Serial.print(steps_rev);\n" +
    "  Serial.print(\" \");  \n" +
    "  Serial.print(wheel_dia);\n" +
    "  Serial.print(\" \");  \n" +
    "  Serial.println(steps);\n" +
    "  delay(1000);*/\n" +
    "  return steps;  \n" +
    "}\n" +
    "\n" +
    "\n" +
    "void forward(float distance){\n" +
    "  int steps = step(distance/0.023);\n" +
    "  Serial.println(steps);\n" +
    "  for(int step=0; step<steps; step++){\n" +
    "    for(int mask=0; mask<4; mask++){\n" +
    "      for(int pin=0; pin<4; pin++){\n" +
    "        digitalWrite(L_stepper_pins[pin], rev_mask[mask][pin]);\n" +
    "        digitalWrite(R_stepper_pins[pin], fwd_mask[mask][pin]);\n" +
    "      }\n" +
    "      delay(delay_time);\n" +
    "    } \n" +
    "  }\n" +
    "}\n" +
    "\n" +
    "void backward(float distance){\n" +
    "  int steps = step(distance/0.023);\n" +
    "  for(int step=0; step<steps; step++){\n" +
    "    for(int mask=0; mask<4; mask++){\n" +
    "      for(int pin=0; pin<4; pin++){\n" +
    "        digitalWrite(L_stepper_pins[pin], fwd_mask[mask][pin]);\n" +
    "        digitalWrite(R_stepper_pins[pin], rev_mask[mask][pin]);\n" +
    "      }\n" +
    "      delay(delay_time);\n" +
    "    } \n" +
    "  }\n" +
    "}\n" +
    "\n" +
    "\n" +
    "void right(float degrees){\n" +
    "  \n" +
    "  float rotation = degrees/360.0;\n" +
    "  float distance = wheel_base * 3.1412 * rotation;\n" +
    "  int steps = step(distance);\n" +
    "  for(int step=0; step<steps; step++){\n" +
    "    for(int mask=0; mask<4; mask++){\n" +
    "      for(int pin=0; pin<4; pin++){\n" +
    "        digitalWrite(R_stepper_pins[pin], rev_mask[mask][pin]);\n" +
    "        digitalWrite(L_stepper_pins[pin], rev_mask[mask][pin]);\n" +
    "      }\n" +
    "      delay(delay_time);\n" +
    "    } \n" +
    "  }   \n" +
    "  \n" +
    "}\n" +
    "\n" +
    "\n" +
    "void left(float degrees){\n" +
    "  \n" +
    "  float rotation = degrees / 360.0;\n" +
    "  float distance = wheel_base * 3.1412 * rotation;\n" +
    "  int steps = step(distance);\n" +
    "  for(int step=0; step<steps; step++){\n" +
    "    for(int mask=0; mask<4; mask++){\n" +
    "      for(int pin=0; pin<4; pin++){\n" +
    "        digitalWrite(R_stepper_pins[pin], fwd_mask[mask][pin]);\n" +
    "        digitalWrite(L_stepper_pins[pin], fwd_mask[mask][pin]);\n" +
    "      }\n" +
    "      delay(delay_time);\n" +
    "    } \n" +
    "  }  \n" +
    "   \n" +
    "}\n" +
    "\n" +
    "\n" +
    "void done(){ // unlock stepper to save battery\n" +
    "  for(int mask=0; mask<4; mask++){\n" +
    "    for(int pin=0; pin<4; pin++){\n" +
    "      digitalWrite(R_stepper_pins[pin], LOW);\n" +
    "      digitalWrite(L_stepper_pins[pin], LOW);\n" +
    "    }\n" +
    "    delay(delay_time);\n" +
    "  }\n" +
    "}\n" +
    "\n" +
    "\n" +
    "void penup(){\n" +
    "  delay(250);\n" +
    "  Serial.println(\"PEN_UP()\");\n" +
    "  penServo.write(PEN_UP);\n" +
    "  delay(250);\n" +
    "}\n" +
    "\n" +
    "\n" +
    "void pendown(){\n" +
    "  delay(250);  \n" +
    "  Serial.println(\"PEN_DOWN()\");\n" +
    "  penServo.write(PEN_DOWN);\n" +
    "  delay(250);\n" +
    "}\n"+ metodos(preparar(funciones));
  
    return code;
    }
 
    public String metodos(String entrada){
        String [] funciones = entrada.split(";");
///////////////////////////////////////////////// 
        Boolean bRect=false;
        String rectangle = "\n" +
"void rectangulo(float base,float altura){\n" +
"  pendown();\n" +
"  forward(base);\n" +
"  right(370);\n" +
"  forward(altura);\n" +
"  right(370);\n" +
"  forward(base);\n" +
"  right(370);\n" +
"  forward(altura);\n" +
"  penup();\n" +
"  }\n";
//////////////////////////////////////////////////   
       Boolean bMove=false;
       String move = "\n" +
"void coordenada(float x,float y){\n" +
"  penup();\n" +
"  forward(x);\n" +
"  right(370);\n" +
"  forward(y);\n" +
"  }\n";
//////////////////////////////////////////////////
       Boolean bTriangulo=false;
       String triangle = "\n" +
"void triangulo(float tamano){ "+
"  pendown();\n" +
"  forward(tamano);\n" +
"  left(493);\n" +
"  forward(tamano);\n" +
"  left(493);\n" +
"  forward(tamano);\n" +
"  left(493);\n" +
"  penup();\n" +
"  forward(tamano);\n" +
"  forward(tamano*0.40);\n" + 
"}\n";
////////////////////////////////////////////////// Aun no funiciona
       Boolean bCirculo=false;
       String circle = "\n" +
"void circulo(float radio){\n" +
"  penup();\n" +
"  forward(x);\n" +
"  rigth(370);\n" +
"  forward(y);\n" +
"  }\n";
//////////////////////////////////
       Boolean bLinea=false;
       String line = "\n" +
"void linea(float distancia){\n" +
"  pendown();\n" +
"  forward(distancia);\n" +
"  }\n";
//////////////////////////////////
        Boolean bA=false;
        String A = "\n" +
"void A(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano*0.55);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  left(370);\n" +
"  pendown();\n" +
"  forward(tamano*0.45);\n" +
"  left(370);\n" +
"  penup();\n" +
"  forward(tamano*0.40);\n" +
"}\n" ;
////////////////////////////////////////
        Boolean bC=false;
        String C = "\n" +
"void C (float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  forward(tamano*0.40);\n" +
"}\n";
/////////////////////////////////////        
        Boolean bE=false;
        String E = "\n" +
"void E(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  forward(tamano*0.40); \n" +
"}\n";
//////////////////////////////////
        Boolean bF=false;
        String F = "\n" +
"void EFE(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  forward(tamano*0.40); \n" +
"}\n";
////////////////////////////////////////
        Boolean bG=false;
        String G = "\n" +
"void G(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  right(370);\n" +
"  backward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano*0.3);\n" +
"  penup();\n" +
"  backward(tamano*0.3);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano*0.40); \n" +
"}\n";
//////////////////////////////////////
        Boolean bH=false;
        String H = "\n" +
"void H(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  pendown();\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  pendown();\n" +
"  backward(tamano/2);\n" +
"  penup();\n" +
"  right(370);\n" +
"  forward(tamano*0.40); \n" +
"}\n";
///////////////////////////////////
        Boolean bI=false;
        String I = "\n" +
"void I(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  penup();\n" +
"  backward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano*0.40); \n" +
"}\n";
/////////////////////////////////
        Boolean bJ=false;
        String J = "\n" +
"void J (float tamano){\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  backward(tamano/2);\n" +
"  penup();\n" +
"  forward(tamano/2);\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  right(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano*0.40); \n" +
"}\n";
/////////////////////////////////    
        Boolean bL=false;
        String L = "\n" +
"void L(float tamano){\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  backward(tamano/2);\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  penup();\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano*0.40); \n" +
"}\n";
////////////////////////////////
        Boolean bO=false;
        String O = "\n" +
"void O(float tamano){\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  penup();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  forward(tamano*0.40); \n" +
"}\n" ;
///////////////////////////////
        Boolean bP=false;
        String P = "\n" +
"void P(float tamano){\n" +
"  pendown();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  forward(tamano*0.40);\n" +
"}\n";
///////////////////////////////
        Boolean bS=false;
        String S = "\n" +
"void S(float tamano){\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  right(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano*0.40);\n" +
"}\n";
//////////////////////////////////
        Boolean bT=false;
        String T = "\n" +
"void T(float tamano){\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  pendown();\n" +
"  forward(tamano);\n" +
"  right(370);\n" +
"  backward(tamano/2);\n" +
"  penup();\n" +
"  forward(tamano/2);\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  right(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano*0.40);\n" +
"}\n";
//////////////////////////////////
        Boolean bU=false;
        String U = "\n" +
"void U(float tamano){\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  penup();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  pendown();\n" +
"  forward(tamano);\n" +
"  penup();\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  forward(tamano*0.40);\n" +
"}\n";
//////////////////////////////////
        Boolean bZ=false;
        String Z = "\n" +
"void Z(float tamano){\n" +
"  left(370);\n" +
"  pendown();\n" +
"  forward(tamano/2);\n" +
"  right(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  penup();\n" +
"  left(370);\n" +
"  forward(tamano);\n" +
"  left(370);\n" +
"  forward(tamano/2);\n" +
"  forward(tamano*0.40); \n" +
"}";
/////////////////////////////////////////////
        String salida="";
    
        for(int i=0;i<funciones.length;i++){
            String parametro = funciones[i].split("\\(")[0].trim();
            if((parametro.equals("coordenada")) && (!bMove)){
                salida=salida+move;
                bMove=true;
            }
            if((parametro.equals("rectangulo")) && (!bRect)){
                salida=salida+rectangle;
                bRect=true;
            }
            if((parametro.equals("circulo")) && (!bCirculo)){
                salida=salida+circle;
                bCirculo=true;
            }
            if((parametro.equals("triangulo")) && (!bTriangulo)){
                salida=salida+triangle;
                bTriangulo=true;
            }
            if((parametro.equals("linea")) && (!bLinea)){
                salida=salida+line;
                bLinea=true;
            }
            if((parametro.equals("A")) && (!bA)){
                salida=salida+A;
                bA=true;
            }
            if((parametro.equals("C")) && (!bC)){
                salida=salida+C;
                bC=true;
            }
            if((parametro.equals("E")) && (!bE)){
                salida=salida+E;
                bE=true;
            }
            if((parametro.equals("F")) && (!bF)){
                salida=salida+F;
                bF=true;
            }
            if((parametro.equals("G")) && (!bG)){
                salida=salida+G;
                bG=true;
            }
            if((parametro.equals("H")) && (!bH)){
                salida=salida+H;
                bH=true;
            }
            if((parametro.equals("I")) && (!bI)){
                salida=salida+I;
                bI=true;
            }
            if((parametro.equals("J")) && (!bJ)){
                salida=salida+J;
                bJ=true;
            }
            if((parametro.equals("L")) && (!bL)){
                salida=salida+L;
                bL=true;
            }
            if((parametro.equals("O")) && (!bO)){
                salida=salida+O;
                bO=true;
            }
            if((parametro.equals("P")) && (!bP)){
                salida=salida+P;
                bP=true;
            }
            if((parametro.equals("S")) && (!bS)){
                salida=salida+S;
                bS=true;
            }
            if((parametro.equals("T")) && (!bT)){
                salida=salida+T;
                bT=true;
            }
            if((parametro.equals("U")) && (!bU)){
                salida=salida+U;
                bU=true;
            }
            if((parametro.equals("Z")) && (!bZ)){
                salida=salida+Z;
                bZ=true;
            }
        }
        return salida;
    }
   
    
}
