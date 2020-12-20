/**
 * La clase representa a una lista de 
 * números enteros
 * 
 * @author - Elorri Oloritz
 * 
 */
import java.util.Arrays;

public class ListaNumeros 
{
    private int[] lista;
    private int pos;

    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros(int n) {
        lista = new int[n];
        pos = 0;
    }

    /**
     * Constructor para pruebas
     *
     */
    public ListaNumeros() {
        lista = new int[]{9,3,7,4,9,2,5,8,12,45};
        pos = 0;
    }

    /**
     * Añade un valor siempre al principio de la lista
     * 
     * @param numero el valor que se añade. No se hace nada si la lista está
     *               completa
     * @return true si se ha podido añadir, false en otro caso
     */
    public boolean addElemento(int numero) {
        if (pos < lista.length){
            for(int i = pos; i != 0; i--){
                lista[i] = lista[i - 1];
            }
            pos++;
            lista[0] = numero;
            return true;
        }
        else{ 
            return false;
        }
    }

    /**
     * devuelve true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return pos == lista.length;
    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. 
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
        for (int i = 0; i < lista.length; i++){
            lista[i] = 0;
        }
    }

    /**
     * Representación textual de la lista de la forma indicada 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
        String str = "";
        if (!estaVacia()){
            for (int i = 0; i < lista.length; i++){
                str += String.format("%8d",lista[i]);
            }
            str += "\n";
            for (int i = 0; i < lista.length; i++){
                str += String.format("%8d",i);
            }
        }
        return str;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     * Búsqueda lineal de numero en la lista
     * @param numero el nº a buscar
     * @return un array con las posiciones en las que se ha encontrado
     *  
     */
    public int[] buscarPosicionesDe(int numero) {
        int[] posicion;
        int longitud = 0;
        int contador = 0;
        for (int i = 0; i < lista.length; i++){
            if (numero == lista[i]){
                longitud++;
            }
        }
        posicion = new int[longitud];
        for (int i = 0; i < lista.length; i++){
            if (numero == lista[i]){
                posicion[contador] = i;
                contador++;
            }
        }
        return posicion;
    }

    /**
     * Hace una búsqueda binaria del numero indicado devolviendo -1 si no se
     * encuentra o la posición en la que aparece
     * 
     * El array original lista no se modifica 
     * Para ello crea previamente una copia
     * de lista y trabaja con la copia
     * 
     * Usa exclusivamente métodos de la clase Arrays
     * 
     */
    public int buscarBinario(int numero) {
        int[] copia = Arrays.copyOf(lista, (lista.length - 1));
        Arrays.sort(copia);
        int posicion = Arrays.binarySearch(copia, numero);
        if (posicion < 0){
            posicion = -1;
        }
        return posicion;
    }

    /**
     * borra el primer elemento de la lista
     */
    public void borrarPrimero() {
        for (int i = 0; i < lista.length - 1; i++){
            lista[i] = lista[i + 1];
        }
        if (!estaVacia()){
            pos--;
        }
        else {
            System.out.println("La lista está vacía");
        }
    }

    /**
     *  Invierte cada uno de los grupos de n elementos que hay en lista
     *  
     *  Si el nº de elementos en lista no es divisible entre n los elementos restantes 
     *  quedan igual
     *  
     *  (leer enunciado)
     *  
     */
    public void invertir(int n) {
        int grupos = lista.length / n;
        int longitud = 0;
        int numero = n;
        int mitad = n / 2;
        for (int i = 0; i < grupos; i++){
            for (int j = 0; j < mitad; j++){
                int aux = lista[longitud];
                lista[longitud] = lista[numero - (j + 1)];
                lista[numero - (j + 1)] = aux;
                longitud++;
            }
            numero += n;
            longitud += mitad;
            if (n % 2 != 0){
                longitud++;
            }
        }
    }

    /**
     * devuelve un ragged array de 2 dimensiones con tantas filas como valores
     * tenga el atributo lista y rellena el array de la forma indicada
     * (leer enunciado)
     * 
     */
    public int[][] toArray2D() {
        int[][] ragged = new int[lista.length][];
        for (int i = 0; i < lista.length; i++){
            ragged[i] = new int[i + 1];
            ragged[i][0] = 1;
            ragged[i][ragged[i].length - 1] = 1;
            for (int cont = 1; cont < i; cont++){
                for (int j = 0; j < cont; j++){
                    ragged[i][j + 1] = ragged[i - 1][j] 
                    + ragged[i - 1][j + 1];
                }
            }
        }
        return ragged;
    }

    /**
     * Punto de entrada a la aplicación 
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) {
        ListaNumeros lista = new ListaNumeros(11);

        System.out.println("------ addElemento() y toString() ------\n");
        int[] valores = {21, -5, 6, -7, 21, -17, 21, 15, 22, 21, 77};
        for (int i = 0; i < valores.length; i++) {
            lista.addElemento(valores[i]);
        }
        System.out.println(lista.toString());
        System.out.println();

        System.out.println("------ buscarPosiciones() ------\n");
        int numero = 21;
        System.out.println(lista.toString());
        System.out.println();
        System.out.println(numero + " aparece en posiciones: " + 
            Arrays.toString(lista.buscarPosicionesDe(numero)));
        System.out.println();

        System.out.println("------ buscarBinario() ------\n");
        if (lista.buscarBinario(numero) >= 0){
            System.out.println("El numero " + numero + " está en la lista");
        }
        else{
            System.out.println("No se encuentra el numero " + numero +
                " en la lista");
        }
        System.out.println();

        System.out.println("------ invertir() ------\n");
        System.out.println("Lista original:");
        System.out.println(lista.toString());
        System.out.println("\nLista invertida por grupos de 2:");
        lista.invertir(2);
        System.out.println(lista.toString());
        System.out.println();

        System.out.println("------ toArray2D() ------\n");
        int[][] array = lista.toArray2D();
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                System.out.printf("%5d",array[i][j]);
            }
            System.out.println();
        }
    }
}
