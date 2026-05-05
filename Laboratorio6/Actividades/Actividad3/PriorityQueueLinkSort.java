package Lab06.actividades.actividad3;

class PriorityQueueLinkSort<E, N> implements PriorityQueue<E, N> {
	 class EntryNode {
	 E data;
	 N priority;
	 EntryNode(E data, N priority) {
		 this.data = data;
		 this.priority = priority;
		 }
		 }
	 private Node<EntryNode> first;
	 private Node<EntryNode> last;
	 public PriorityQueueLinkSort() {
	 this.first = null;
	 this.last = null;
	 }
	 
	 public void enqueue(E x, N pr) {
			EntryNode newEntry = new EntryNode(x, pr);
			Node<EntryNode> newNode = new Node<>(newEntry);
			
			if (isEmpty()) {
				this.first = newNode;
				this.last = newNode;
				return;
			}

			if (((Comparable<N>) pr).compareTo(this.first.getData().priority) > 0) {
				newNode.setNext(this.first);
				this.first = newNode;
				return;
			}

			Node<EntryNode> actual = this.first;
			while (actual.getNext() != null && 
			       ((Comparable<N>) actual.getNext().getData().priority).compareTo(pr) >= 0) {
				actual = actual.getNext();
			}

			newNode.setNext(actual.getNext());
			actual.setNext(newNode);

			if (newNode.getNext() == null) {
				this.last = newNode;
			}
		}
	 
	 public E dequeue() throws ExceptionIsEmpty {
		 if (isEmpty())
			 throw new ExceptionIsEmpty("Queue is empty");
		 E aux = this.first.getData().data;
		 this.first = this.first.getNext();
		 if (this.first == null)
		 this.last = null;
		 return aux;
		 }
	 
	 public E first() throws ExceptionIsEmpty {
			if (isEmpty()) {
				throw new ExceptionIsEmpty("La cola está vacía");
			}
			return this.first.getData().data;
		}

	 public E last() throws ExceptionIsEmpty {
			if (isEmpty()) {
				throw new ExceptionIsEmpty("La cola está vacía");
			}
			return this.last.getData().data;
		}
	 
	 public boolean isEmpty() {
			return this.first == null;
		}
	 
	 public String toString() {
			if (isEmpty()) {
				return "[]";
			}
			
			StringBuilder sb = new StringBuilder("[");
			Node<EntryNode> actual = this.first;
			
			while (actual != null) {
				sb.append("(").append(actual.getData().data).append(", pr:").append(actual.getData().priority).append(")");
				if (actual.getNext() != null) {
					sb.append(" -> "); // Una flecha para visualizar los enlaces
				}
				actual = actual.getNext();
			}
			sb.append("]");
			return sb.toString();
		}
}



	 
	 

	 