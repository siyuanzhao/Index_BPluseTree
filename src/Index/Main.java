package Index;

import java.util.ArrayList;

public class Main{
	public static void main(String[] args) {
		BPTree<Integer> bpTree1=new BPTree<>(4, 10);
		BPTree<String> bpTree2=new BPTree<>(6, 10);

		DBObject movies=new DBObject();
		ArrayList<DBObject> listToStoreDB= new ArrayList<DBObject>();
		listToStoreDB=movies.read();
		IndexInterface<Integer> indexInterFace1=new IndexInterface<>(bpTree1);
		IndexInterface<String> indexInterFace2=new IndexInterface<>(bpTree2);

		for (int i=0;i<listToStoreDB.size();i++){
			indexInterFace1.put(listToStoreDB.get(i).title, listToStoreDB.get(i).year);
			bpTree1.saveToDisk();
		}
		
		for (int i=0;i<listToStoreDB.size();i++){
			indexInterFace2.put(listToStoreDB.get(i).title, String.valueOf(listToStoreDB.get(i).year)+"|"+listToStoreDB.get(i).format);
			bpTree2.saveToDisk();
		}
		
		String get1=indexInterFace1.get(1977);
		String get2=indexInterFace1.get(1990);
		String get3=indexInterFace1.get(2000);
		
		System.out.println(get1);
		System.out.println();
		System.out.println(get2);
		System.out.println();
		System.out.println(get3);
		System.out.println();
		
		String get4=indexInterFace2.get("1977|DVD");
		String get5=indexInterFace2.get("1990|VHS");
//		String get6=indexInterFace2.get("2000|DVD");
		
		System.out.println(get4);
		System.out.println();
		System.out.println("get5 is " + get5);
		System.out.println();
//		System.out.println(get6);
		System.out.println(indexInterFace1.get(1990));
		System.out.println();
		indexInterFace1.remove("Pacific Heights");
		bpTree1.saveToDisk();
		System.out.println(indexInterFace1.get(1990));

		
		bpTree1.saveToDisk();
		
		
	}
	
}
