/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HOME
 */
import java.util.*; 
import java.lang.*; 
import java.io.*; 

public class EjercutarTXT {
    
        
    //una funcion para encontrar el nodo más corto
    int minimaDistancia(int dist[], Boolean paso[], int V)
    {
        //valores previo antes de la comparación
        int min= Integer.MAX_VALUE;
        int minNodo= -1;
        
        //pasa por cada nodo
        for(int i=0; i<V;i++)
        {
            //si aun no ha pasado por ese nodo y si su distancia es menor al anterior, entra al if
            if(paso[i]== false && dist[i]<=min)
            {
                //esto significa que encontro un valor menor
                min=dist[i];
                minNodo= i;
                
            }
        }
        //retorna el nodo con la distancia más corta
        return minNodo;
        
    }
    void printSolution(int dist[],int V,int principal) 
    { 
        System.out.println("Vertice \t Distancia del Vertice Base: "+ principal); 
        for (int i = 0; i < V; i++)
            //cada nodo tiene su distancia hacia el nodo principal
            System.out.println(i + " \t\t " + dist[i]); 
    } 
    
    void dijkstra(int graph[][],int V,int principal)
    {
        int dist[]=new int[V];//se crea una lista del tamaño de la cantidad de vertices
        Boolean paso[]= new Boolean[V]; //de igual manera, esta lista muestras los vertices que se paso previamente
        
        //recorrer todos los vertices y colocarles el peso y "si paso" correspondiente 
        for(int i=0;i<V;i++)
        {
            dist[i]=Integer.MAX_VALUE;
            paso[i]=false;
            
        }
        //siempre el nodo base sera de peso 0.
        dist[principal]=0;
        
        //En cada vertice se busca uno por uno y viendo cual es el peso más pequeño
        for(int i=0;i< V-1;i++)
        {
            //Se encuentra el nodo mas cercano
            int minNodo=minimaDistancia(dist,paso,V);
            
            //dicho nodo dice que ya paso
            paso[minNodo]=true;
            //revisar si para llegar a ese nodo paso por otros
            
            //esto es para ver si: 
            //1) si no ha pasado por el vertice
            //2) no es el nodo principal
            //3) si aun no han llegado a ese nodo
            //4) si la suma de la distancia es menor a la distancia previa
            for(int y=0;y< V;y++)
            {
                if(!paso[y] && graph[minNodo][y] != 0 && dist[minNodo] != Integer.MAX_VALUE && dist[minNodo] + graph[minNodo][y] < dist[y])
                {
                    //seteamos la nueva distancia del nodo al nodo principal.
                    dist[y] = dist[minNodo] + graph[minNodo][y];
                }
            }
        }
        this.printSolution(dist,V,principal);
    }
     
  
    //el plan a futuro es que los valores de entrada se coloquen en un txt para mayor comodidad.
    //cada valor dentro del nodo será separados por comas
    //cada nodo será separado por lineas.
    
    public static void main(String[] args) throws FileNotFoundException, IOException 
    { 

        //es el lector de TXT, colocar la direccion del TXT con doble "\"    
        BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\HOME\\Desktop\\Redes Computadora\\Trabajo\\TXT\\prueba01.txt"));
        
        String bfread;
            
        bfread=bf.readLine();
        int vert=Integer.parseInt(bfread);//lee la primera fila del TXT y le asigna como valor de los nodos
        bfread=bf.readLine();
        int principal=Integer.parseInt(bfread);
        int graph[][]=new int [vert][vert];//primero columna y segundo fila
        int columna=0;
        int fila=0;
        String[] data=null;//donde se guardara el tamaño de los vertices temporalmente
        while((bfread= bf.readLine()) !=null)
                {
                    
                    data=bfread.split(",");//separador por comas
                    for(String value : data){//del arreglo data extrae todos los valores
                        
                        //agrega los valores a la matriz
                        graph[columna][fila]=Integer.parseInt(value);
                        
                        fila++;
                    }
                    columna++;
                    fila=0;
                    
                }
            //int v=Integer.parseInt(temp);
            //System.out.println(temp);
            //System.out.println(a);
            
        EjercutarTXT prueba = new EjercutarTXT(); 
        prueba.dijkstra(graph,vert,principal); 
            
        
        
        
    } 
}
