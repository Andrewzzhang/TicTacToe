
public class DoubleLinkedNode<T>
{
	private DoubleLinkedNode<T> prev;
	private DoubleLinkedNode<T> next;
	private T element;
	
	public DoubleLinkedNode()
	{
		this.prev = null;
		this.next = null;
		this.element = null;
	}
	
	public DoubleLinkedNode(T element)
	{
		this.prev = null;
		this.next = null;
		this.element = element;
	}
	
	public T getElement()
	{
		return this.element;
	}
	
	public void setElement(T newElement)
	{
		this.element = newElement;
	}
	
	public DoubleLinkedNode<T> getNext()
	{
		return next;
	}
	
	public void setNext(DoubleLinkedNode<T> newNext)
	{
		this.next = newNext;
	}
	
	public DoubleLinkedNode<T> getPrev()
	{
		return prev;
	}
	
	public void setPrev(DoubleLinkedNode<T> newPrev)
	{
		this.prev = newPrev;
	}
	
	public void remove()
	{
		if(this.getPrev() == null && this.getNext() == null)
		{
			this.element = null;
		}
		else if(this.getPrev() == null)
		{
			this.getNext().setPrev(this.getPrev());
		}
		else if(this.getNext() == null)
		{
			this.getPrev().setNext(this.getNext());
		}
		else 
		{
			this.getPrev().setNext(this.getNext());
			this.getNext().setPrev(this.getPrev());
		}
	}
}
