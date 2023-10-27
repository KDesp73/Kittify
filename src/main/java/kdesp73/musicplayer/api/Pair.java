/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

/**
 *
 * @author konstantinos
 * @param <T>
 * @param <E>
 */
public class Pair<T, E> {
	public T first;
	public E second;
	
	
	public Pair(){}
	public Pair(T first, E second){
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString() {
		return "Pair{" + "first=" + first + ", second=" + second + '}';
	}
	
	
}
