package hello;

public class Status {
    private int totalWords;
    private int totalRequests;
    private int avgProcessingTimeNs;

    public Status(int totalWords,int totalRequests,int avgProcessingTimeNs) {
        this.totalWords = totalWords;
	this.totalRequests = totalRequests;
	this.avgProcessingTimeNs = avgProcessingTimeNs;
    }

    public void setTotalWords(int totalWords)
    {
	this.totalWords = totalWords;
    }
    public void setTotalRequests(int totalRequests)
    {
	this.totalRequests = totalRequests;
    }
    public void setAvgProcessingTimeNs(int avgProcessingTimeNs)
    {
	this.avgProcessingTimeNs = avgProcessingTimeNs;
    }
    public int getTotalWords() {
        return totalWords;
    }
    public int getTotalRequests() {
        return totalRequests;
    }
    public int getAvgProcessingTimeNs() {
        return avgProcessingTimeNs;
    }
}

