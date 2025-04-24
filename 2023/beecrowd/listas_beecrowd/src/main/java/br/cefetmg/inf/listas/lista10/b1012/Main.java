
package br.cefetmg.inf.listas.lista10.b1012;

import java.util.Scanner;
import java.util.Locale;

class Figura {
    private double lado;
    
    public Figura(double lado) {
        this.lado = lado;
    }
    
    public double getArea() {
        return lado;
    }
    
    public double getLado() {
        return lado;
    }
}

class Triangulo extends Figura {
    private double altura;
    
    public Triangulo(double base, double altura) {
        super(base);
        this.altura = altura;
    }
    
    public double getArea() {
        return (getLado() * altura) / 2;
    }
}

class Circulo extends Figura{
    private final double pi;
    
    public Circulo(double raio) {
        super(raio);
        pi = 3.14159;
    }
    
    public double getArea() {
        return getLado() * getLado() * pi;
    }
    
}

class Trapezio extends Figura {
    private double baseMaior;
    private double baseMenor;
    
    public Trapezio(double altura, double b1, double b2) {
        super(altura);
        baseMaior = b1;
        baseMenor = b2;
    }
    
    public double getArea() {
        return (baseMaior + baseMenor)/2 * getLado();
    }
}

class Quadrado extends Figura {
    public Quadrado(double lado) {
        super(lado);
    }
    
    public double getArea() {
        return getLado() * getLado();
    }
}

class Retangulo extends Figura {
    private double altura;
    
    public Retangulo(double lado, double altura) {
        super(lado);
        this.altura = altura;
    }
    
    public double getArea() {
        return altura * getLado();
    }
}


public class Main {
    public static void main(String args[]) {
        double a, b, c;
        
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(System.in);
        
        a = in.nextDouble();
        b = in.nextDouble();
        c = in.nextDouble();
        
        Triangulo tri = new Triangulo(a, c);
        Circulo cir = new Circulo(c);
        Trapezio tra = new Trapezio(c, a, b);
        Quadrado qua = new Quadrado(b);
        Retangulo ret = new Retangulo(a, b);
        
        System.out.printf("TRIANGULO: %.3f\n", tri.getArea());
        System.out.printf("CIRCULO: %.3f\n", cir.getArea());
        System.out.printf("TRAPEZIO: %.3f\n", tra.getArea());
        System.out.printf("QUADRADO: %.3f\n", qua.getArea());
        System.out.printf("RETANGULO: %.3f\n", ret.getArea());
        
    }
}
