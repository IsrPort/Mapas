/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapas;
import java.util.Stack;
/**
 *
 * @author Practica IA1
 */
public class GeneradorMatriz {
    
    int [][] regiones;
    int cantidadColores;
    int contadorEspaciosAsignados;
    boolean lleno;
    
    public GeneradorMatriz(int [][] reg, int colores){
        this.regiones= reg;
        this.cantidadColores= colores;
        this.contadorEspaciosAsignados=0;
    }
    
    public int[][] profundidadDFS(){
        Stack<int[][]> pila = new Stack();
        pila.push(regiones);
        lleno=false;
        int [][] aux;
        int [][] res = new int[7][7];
        
        while(!pila.isEmpty()&&!lleno){
            aux=pila.pop();
            if(noExisteCeros(aux)){
                lleno = true;
                regiones = aux;
            }else{
                int [][] copia;
                for(int i=1; i<= cantidadColores; i++){
                    copia = copiar(aux,i);
                    if(isValid(copia)){
                        contadorEspaciosAsignados++;
                        System.out.println("=============="+contadorEspaciosAsignados+"===========");
                        mostrar(copia);
                        res = copia;
                        pila.push(copia);
                    }
                }
            } 
        }
        return res;
    } 
  
    
    public boolean noExisteCeros(int [][] m){
        boolean res = true;
        for(int i=0;i<m.length&&res;i++){
            for(int j=0; j<m[i].length&&res;j++){
                if(m[i][j]==0){
                    res = false;
                }
            }
        }
        return res;
    }
    
    public int [][] copiar(int [][] mat, int color){
        int[][] res = new int[mat.length][mat[0].length];
        boolean flag=true;
        for(int i=0;i<mat.length&&flag;i++){
            for(int j=0; j<mat[i].length&&flag;j++){
                if(mat[i][j]!=0){
                    res[i][j]=mat[i][j];
                }else{
                    res[i][j] = color;
                    flag=false;
                }
            }
        }
        return res;
    }
    
    public boolean isValid(int[][] m){
        boolean res=true;
        for(int i=0;i<m.length&&res;i++){
            for(int j=0; j<m[i].length&&res;j++){
                if(m[i][j]!=0){
                    if(exists(i,j-1)&&res)
                        res=m[i][j]!=m[i][j-1];
                    if(exists(i-1,j-1)&&res)
                        res=m[i][j]!=m[i-1][j-1];
                    if(exists(i-1,j)&&res)
                        res=m[i][j]!=m[i-1][j];
                    if(exists(i-1,j+1)&&res)
                        res=m[i][j]!=m[i-1][j+1];
                    if(exists(i,j+1)&&res)
                        res=m[i][j]!=m[i][j+1];
                    if(exists(i+1,j+1)&&res)
                        res=m[i][j]!=m[i+1][j+1];
                    if(exists(i+1,j)&&res)
                        res=m[i][j]!=m[i+1][j];
                    if(exists(i+1,j-1)&&res)
                        res=m[i][j]!=m[i][j-1];
                               
                }
            }
        }
        return res;
    }
    
    public void mostrar(int[][] m){
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[i].length;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public boolean exists(int i, int j){
        int x = regiones.length;
        int y = regiones[0].length;
        return i>=0 && i<x && j>=0 && j<y;
    }
    
   
}
