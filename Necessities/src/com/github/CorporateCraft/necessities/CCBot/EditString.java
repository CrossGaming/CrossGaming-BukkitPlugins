package com.github.CorporateCraft.necessities.CCBot;

public class EditString
{
	public EditString()
	{
		
	}
    public String RemoveLet(String strinput)
    {
        strinput = RemoveLowLet(strinput);
        strinput = RemoveCapLet(strinput);
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveNonLet(String strinput)
    {
        strinput = RemoveNum(strinput);
        strinput = RemoveSymb(strinput);
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveNonNumb(String strinput)
    {
        strinput = RemoveLet(strinput);
        strinput = RemoveSymb(strinput);
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveNonSymb(String strinput)
    {
        strinput = RemoveNum(strinput);
        strinput = RemoveLet(strinput);
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveNum(String strinput)
    {
        strinput = strinput.replaceAll("0", "");
        strinput = strinput.replaceAll("1", "");
        strinput = strinput.replaceAll("2", "");
        strinput = strinput.replaceAll("3", "");
        strinput = strinput.replaceAll("4", "");
        strinput = strinput.replaceAll("5", "");
        strinput = strinput.replaceAll("6", "");
        strinput = strinput.replaceAll("7", "");
        strinput = strinput.replaceAll("8", "");
        strinput = strinput.replaceAll("9", "");
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveLowLet(String strinput)
    {
        strinput = strinput.replaceAll("a", "");
        strinput = strinput.replaceAll("b", "");
        strinput = strinput.replaceAll("c", "");
        strinput = strinput.replaceAll("d", "");
        strinput = strinput.replaceAll("e", "");
        strinput = strinput.replaceAll("f", "");
        strinput = strinput.replaceAll("g", "");
        strinput = strinput.replaceAll("h", "");
        strinput = strinput.replaceAll("i", "");
        strinput = strinput.replaceAll("j", "");
        strinput = strinput.replaceAll("k", "");
        strinput = strinput.replaceAll("l", "");
        strinput = strinput.replaceAll("m", "");
        strinput = strinput.replaceAll("n", "");
        strinput = strinput.replaceAll("o", "");
        strinput = strinput.replaceAll("p", "");
        strinput = strinput.replaceAll("q", "");
        strinput = strinput.replaceAll("r", "");
        strinput = strinput.replaceAll("s", "");
        strinput = strinput.replaceAll("t", "");
        strinput = strinput.replaceAll("u", "");
        strinput = strinput.replaceAll("v", "");
        strinput = strinput.replaceAll("w", "");
        strinput = strinput.replaceAll("x", "");
        strinput = strinput.replaceAll("y", "");
        strinput = strinput.replaceAll("z", "");
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveCapLet(String strinput)
    {
        strinput = strinput.replaceAll("A", "");
        strinput = strinput.replaceAll("B", "");
        strinput = strinput.replaceAll("C", "");
        strinput = strinput.replaceAll("D", "");
        strinput = strinput.replaceAll("E", "");
        strinput = strinput.replaceAll("F", "");
        strinput = strinput.replaceAll("G", "");
        strinput = strinput.replaceAll("H", "");
        strinput = strinput.replaceAll("I", "");
        strinput = strinput.replaceAll("J", "");
        strinput = strinput.replaceAll("K", "");
        strinput = strinput.replaceAll("L", "");
        strinput = strinput.replaceAll("M", "");
        strinput = strinput.replaceAll("N", "");
        strinput = strinput.replaceAll("O", "");
        strinput = strinput.replaceAll("P", "");
        strinput = strinput.replaceAll("Q", "");
        strinput = strinput.replaceAll("R", "");
        strinput = strinput.replaceAll("S", "");
        strinput = strinput.replaceAll("T", "");
        strinput = strinput.replaceAll("U", "");
        strinput = strinput.replaceAll("V", "");
        strinput = strinput.replaceAll("W", "");
        strinput = strinput.replaceAll("X", "");
        strinput = strinput.replaceAll("Y", "");
        strinput = strinput.replaceAll("Z", "");
        strinput = strinput.trim();
        return strinput;
    }

    public String RemoveSymb(String strinput)
    {
        strinput = strinput.replaceAll("!", "");
        strinput = strinput.replaceAll("@", "");
        strinput = strinput.replaceAll("#", "");
        strinput = strinput.replaceAll("$", "");
        strinput = strinput.replaceAll("%", "");
        strinput = strinput.replaceAll("^", "");
        strinput = strinput.replaceAll("&", "");
        //strinput = strinput.replaceAll("*", "");
        //strinput = strinput.replaceAll("(", "");
        //strinput = strinput.replaceAll(")", "");
        strinput = strinput.replaceAll("_", "");
        strinput = strinput.replaceAll("-", "");
        //strinput = strinput.replaceAll("+", "");
        strinput = strinput.replaceAll("=", "");
        strinput = strinput.replaceAll("~", "");
        strinput = strinput.replaceAll("`", "");
        //strinput = strinput.replaceAll("[", "");
        //strinput = strinput.replaceAll("{", "");
        //strinput = strinput.replaceAll("]", "");
        //strinput = strinput.replaceAll("}", "");
        strinput = strinput.replaceAll("|", "");
        strinput = strinput.replaceAll(";", "");
        strinput = strinput.replaceAll(":", "");
        strinput = strinput.replaceAll("'", "");
        strinput = strinput.replaceAll(",", "");
        strinput = strinput.replaceAll("<", "");
        //strinput = strinput.replaceAll(".", "");
        strinput = strinput.replaceAll(">", "");
        //strinput = strinput.replaceAll("?", "");
        strinput = strinput.replaceAll("/", "");
        //strinput = strinput.replaceAll("\"", "");
        //strinput = strinput.replaceAll("\\", "");
        strinput = strinput.trim();
        return strinput;
    }
}