package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataReader {
	// Utilities 
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// Read JSON to String

				String JsonContent = FileUtils.readFileToString(
						new File(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\PurchaseOrder.json"),
						StandardCharsets.UTF_8);
				
				// Convert String To Hash MAP - Jackson ( Maven Repo )

				ObjectMapper mapper = new ObjectMapper();

				List<HashMap<String, String>> data = mapper.readValue(JsonContent,
						new TypeReference<List<HashMap<String, String>>>() {
						}); // Default IMP
				
				return data;

	}

}
