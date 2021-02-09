package Classification;
import java.util.List;

import biz.k11i.xgboost.Predictor;
import biz.k11i.xgboost.util.FVec;


public class XgboostPredictor {
	static String prediction = "DMY";
	static float[] toFloatArray(Integer[] targetArray) {
		  if (targetArray == null) return null;
		  int n = targetArray.length;
		  float[] ret = new float[n];
		  for (int i = 0; i < n; i++) {
		    ret[i] = (float)targetArray[i];
		  }
		  return ret;
		}
    public static void main(String[] args) throws java.io.IOException {
    	
    	
        Predictor predictor = new Predictor(
                new java.io.FileInputStream("/home/hironeo/Desktop/vbee_tts_text_normalization_python/random_forest/model/xgboost_new.model"));
        
        System.out.println("Loading done");
        
        String sentence = "ngày <nsw> 12/1/2019 </nsw>";
        Vectorizator vector = new Vectorizator();
        List<List<Integer>> charCount = vector.makeFeatureSentence(sentence);
//        // Create feature vector from dense representation by array
        List<Integer> denseArray = charCount.get(0);
        Integer[] targetArray = denseArray.toArray(new Integer[0]);
//        float[] finalArray = toFloatArray(targetArray);
        float[] finalArray = new float[140];
        int n = targetArray.length;
		  for (int i = 0; i < n; i++) {
			  finalArray[i] = (float)targetArray[i];
//			 s System.out.println((float)targetArray[i]);
		  }
        FVec fVecDense = FVec.Transformer.fromArray(
        		finalArray,
                false /* treat zero element as N/A */);

        // Predict probability or classification
//        System.out.println(finalArray.length);
//        double[] prediction = predictor.predict(fVecDense);
        System.out.println(sentence);
        System.out.println(prediction);


    }
}