import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVOperator {

public static void main(String[] args) throws FileNotFoundException {
String file = "C:\\Users\\ramba\\Downloads\\Availity\\test.csv";
String line;
String[] initial = null;
Function<InsuranceDetails, Integer> byVersion = insurance -> -Integer.parseInt(insurance.getVersion());
Function<InsuranceDetails, String> byFirstName = insurance -> insurance.getFirstName();
Function<InsuranceDetails, String> byLastName = insurance -> insurance.getLastName();

List<InsuranceDetails> list = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader(file))) {
initial = br.readLine().split(",");
while ((line = br.readLine()) != null) {
String[] split = line.split(",");
InsuranceDetails details = new InsuranceDetails(split[0], split[1], split[2], split[3], split[4]);
list.add(details);
}
Map<String, List<InsuranceDetails>> result = list.stream()
.collect(Collectors.groupingBy(InsuranceDetails::getInsuranceCompany));

for (Map.Entry<String, List<InsuranceDetails>> entry : result.entrySet()) {
String fileName = "C:\\Users\\ramba\\Downloads\\Availity\\output\\" + entry.getKey() + ".csv";
File newFile = new File(fileName);
BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
for (int k = 0; k < initial.length - 1; k++) {
writer.append(initial[k]);
writer.append(',');
}
writer.newLine();

List<InsuranceDetails> collect = entry.getValue().stream().sorted(Comparator.comparing(byFirstName).thenComparing(byLastName).thenComparing(byVersion)).collect(Collectors.toList());

Set<String> visited = new HashSet<String>();

for (InsuranceDetails insuranceDetails : collect) {
String concat = insuranceDetails.getFirstName()+insuranceDetails.getLastName();
if(!visited.contains(concat)) {
visited.add(concat);
writer.append(insuranceDetails.getUserId() + ",");
writer.append(insuranceDetails.getFirstName() + ",");
writer.append(insuranceDetails.getLastName() + ",");
writer.append(insuranceDetails.getVersion() + ",");
writer.newLine();
}
}
writer.flush();
writer.close();
}
} catch (Exception e) {
e.printStackTrace();
}
}
}
