package kr.co.hallabong.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<E1, E2> {
	private E1 item1;
	private E2 item2;
	
	public Pair() {}

	public Pair(E1 item1, E2 item2) {
		this.item1 = item1;
		this.item2 = item2;
	}
}
