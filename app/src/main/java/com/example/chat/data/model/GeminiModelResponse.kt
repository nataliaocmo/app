data class Part(
    val text: String
)

data class Content(
    val parts: List<Part>,
    val role: String
)

data class SafetyRating(
    val category: String,
    val probability: String
)

data class Candidate(
    val content: Content,
    val finishReason: String,
    val index: Int,
    val safetyRatings: List<SafetyRating>
)

data class UsageMetadata(
    val promptTokenCount: Int,
    val candidatesTokenCount: Int,
    val totalTokenCount: Int
)

data class PostResponse(
    val candidates: List<Candidate>,
    val usageMetadata: UsageMetadata
)
