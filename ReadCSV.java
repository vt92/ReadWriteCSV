public class ReadCSV {
	static List<User> users = new ArrayList<User>();

	public static void main(String[] args){
		String filePath="C:\\csvread\\user.csv";
		
		System.out.println("starting read");
		readcsv(filePath);
		
		System.out.println("writing again after sorting by firstname");
		writecsv(filePath);
	} 


	public static void writecsv(String filePath){
		
		Comparator<User> comp = (o1,o2)->{
			String f1 = o1.getFname();
			String f2 = o2.getFname();
			
			if(f1.charAt(0)>f2.charAt(0)) return +1;
			if(f2.charAt(0)>f1.charAt(0)) return -1;
			else return 0;
		};
		users.sort(comp);

		try(FileWriter filewriter= new FileWriter(filePath)){
			filewriter.append("Id, FirstName, LastName\n");
			for (User u : users) {
				filewriter.append(String.valueOf(u.getId()));
				filewriter.append(",");
				filewriter.append(u.getFname());
				filewriter.append(",");
				filewriter.append(u.getLname());
				filewriter.append("\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void readcsv(String filePath){
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			//List<User> users = new ArrayList<>();
			String line="";
			br.readLine();

			while((line= br.readLine()) != null){
				String[] fields = line.split(",");

				if(fields.length>0){
					User user = new User();
					user.setId(Integer.parseInt(fields[0]));
					user.setFname(fields[1]);
					user.setLname(fields[2]);
					users.add(user);
				}
			}
			for (User u : users) {
				System.out.println(u.getId()+"-"+u.getFname()+"-"+u.getLname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
