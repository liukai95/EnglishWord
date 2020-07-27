package word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sort {
	private Map<String, Counter> tm;// 字母升序
	private Map<String, String> lhm2 = new LinkedHashMap<String, String>();// 字母降序,使用LinkedHashMap，按照放的顺序输出
	private Map<String, String> lhm3 = new LinkedHashMap<String, String>();// 词频升序
	private Map<String, String> lhm4 = new LinkedHashMap<String, String>();// 词频降序

	public Map<String, Counter> getTm() {
		return tm;
	}
	public Map<String, String> getLhm2() {
		return lhm2;
	}
	public Map<String, String> getLhm3() {
		return lhm3;
	}
	public Map<String, String> getLhm4() {
		return lhm4;
	}
	
	public void sort(Map<String, Counter> hm) {
		List<String> list1 = new ArrayList<String>();// 字母降序
		List<String> list2 = new ArrayList<String>();// 词频降序
		tm= new TreeMap<String, Counter>(hm);     //对treeMap进行初始化
		Iterator<String> it = tm.keySet().iterator();
		
		while (it.hasNext()) {
			String key = it.next();
			String str = key + " ->" + hm.get(key);
			list1.add(str);   //存放到list1，方便实现字母降序
		}
		Collections.reverse(list1);//把list1的值改为倒序
		for (String value : list1) {
			lhm2.put(value.split("->")[0], value.split("->")[1]);//字母降序,方便导出
		}
		ArrayList<String> keys = new ArrayList<String>(hm.keySet());
		Collections.sort(keys, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				// 按照value的值升序排列
				if (Integer.parseInt(hm.get(o1).toString()) > Integer
						.parseInt(hm.get(o2).toString()))
					return 1;
				else if (Integer.parseInt(hm.get(o1).toString()) == Integer
						.parseInt(hm.get(o2).toString()))
					return 0;
				else
					return -1;
			}
		});
		for (String key : keys) {
			String str = key + "->" + hm.get(key);
			list2.add(str);
			lhm3.put(key, hm.get(key).toString());//词频升序,方便导出;    
		}

		Collections.reverse(list2);  //把list2的值改为倒序
		for (String value : list2) {
			lhm4.put(value.split("->")[0], value.split("->")[1]);//词频降序,方便导出

		}
	}
}
