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
	private Map<String, Counter> tm;// ��ĸ����
	private Map<String, String> lhm2 = new LinkedHashMap<String, String>();// ��ĸ����,ʹ��LinkedHashMap�����շŵ�˳�����
	private Map<String, String> lhm3 = new LinkedHashMap<String, String>();// ��Ƶ����
	private Map<String, String> lhm4 = new LinkedHashMap<String, String>();// ��Ƶ����

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
		List<String> list1 = new ArrayList<String>();// ��ĸ����
		List<String> list2 = new ArrayList<String>();// ��Ƶ����
		tm= new TreeMap<String, Counter>(hm);     //��treeMap���г�ʼ��
		Iterator<String> it = tm.keySet().iterator();
		
		while (it.hasNext()) {
			String key = it.next();
			String str = key + " ->" + hm.get(key);
			list1.add(str);   //��ŵ�list1������ʵ����ĸ����
		}
		Collections.reverse(list1);//��list1��ֵ��Ϊ����
		for (String value : list1) {
			lhm2.put(value.split("->")[0], value.split("->")[1]);//��ĸ����,���㵼��
		}
		ArrayList<String> keys = new ArrayList<String>(hm.keySet());
		Collections.sort(keys, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				// ����value��ֵ��������
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
			lhm3.put(key, hm.get(key).toString());//��Ƶ����,���㵼��;    
		}

		Collections.reverse(list2);  //��list2��ֵ��Ϊ����
		for (String value : list2) {
			lhm4.put(value.split("->")[0], value.split("->")[1]);//��Ƶ����,���㵼��

		}
	}
}
