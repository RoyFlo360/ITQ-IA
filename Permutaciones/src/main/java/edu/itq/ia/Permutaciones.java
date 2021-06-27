package edu.itq.ia;

import java.util.LinkedList;

public class Permutaciones 
{
	private LinkedList<Object> elementList = new LinkedList<>();
	public Permutaciones() 
	{
		elementList = new LinkedList<>();
	}
	
	public Permutaciones(LinkedList<Object> elementList) 
	{
		this.elementList = elementList;
	}
	
	private LinkedList<Object> getElementList() 
	{
		return elementList;
	}
	
	public void setElementList(LinkedList<Object> elementList) 
	{
		this.elementList = elementList;
	}

	public Object getElement(int index) 
	{
		try 
		{
			return getElementList().get(index);
		} 
		catch (IndexOutOfBoundsException e) 
		{
			System.out.println(e);
			return null;
		}		
	}

	public void addElement(Object element) 
	{
		if (element != null)
			getElementList().add(element);
	}
	
	public void permutacionSR(LinkedList<Object> resultList, int r) 
	{
		if (r < 1 || r > getElementList().size())
			return;
		if (resultList.size() == r) 
		{
			System.out.println(resultList);
			return;
		}
		int index = 0;
		while (index < getElementList().size()) 
		{
			Object element = getElement(index);

			if (!resultList.contains(element)) 
			{
				resultList.add(getElement(index));
				permutacionSR(resultList, r);
				resultList.removeLast();
			}
			index++;
		}
	}

	public void permutacionCR(LinkedList<Object> resultList, int r) 
	{
		if (r < 1 || r > getElementList().size())
			return;
		
		if (resultList.size() == r) 
		{
			System.out.println(resultList);
			return;
		}
		
		int index = 0;
		while (index < getElementList().size()) 
		{
			resultList.add(getElement(index));
			permutacionCR(resultList, r);
			resultList.removeLast();
			index++;
		}
	}

	public static void main(String[] args) 
	{

		Permutaciones p = new Permutaciones();
		LinkedList<Object> resultList = new LinkedList<>();
		//Ingrese el numero de elementos para permutar int rango = [cantidad de valores en la lista en numero entero];
		int rango = 2;

		//Ingrese los elementos de la lista para permutar p.addElement('Elemento de la lista');
		p.addElement('A');
		p.addElement('B');
		
		System.out.println("Inicio Permutaciones sin repeticion de elementos.");
		p.permutacionSR(resultList, rango);
		System.out.println("Final Permutaciones sin repeticion de elementos.");
		
		resultList.clear();
		
		System.out.println("Inicio Permutaciones con repeticion de elementos.");
		p.permutacionCR(resultList, rango);
		System.out.println("Final Permutaciones con repeticion de elementos.");
	}
}