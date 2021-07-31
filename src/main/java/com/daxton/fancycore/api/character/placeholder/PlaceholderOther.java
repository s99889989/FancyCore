package com.daxton.fancycore.api.character.placeholder;



public class PlaceholderOther {


    public PlaceholderOther(){


    }
    public static String getOther(String firstString){
        String outputString = "";
        String key = firstString.toLowerCase().replace("<cd_other_","").replace(">","");
        if(key.toLowerCase().contains("math_random_")){
            String randomKey = key.replace("math_random_","");
            try {
                int randomNumber = Integer.valueOf(randomKey);
                outputString = String.valueOf((int)(Math.random()*randomNumber));
            }catch (Exception exception){
                outputString = "The "+ randomKey +" in "+firstString+" can only hold numbers";
            }
        }



        return outputString;
    }


}
