import sys
import json
from sentence_transformers import SentenceTransformer
import chromadb
from chromadb.utils import embedding_functions

# 전역 모델 변수
model = SentenceTransformer('sentence-transformers/all-MiniLM-L6-v2')
embed_fn = embedding_functions.SentenceTransformerEmbeddingFunction(model_name='sentence-transformers/all-MiniLM-L6-v2')

def main():
	# 입력 데이터 읽기
	data = json.loads(sys.stdin.read())
	question_id = data['question_id']
	question = data['question']

	print(question_id, question)

	# 임베딩 생성
	embedding = model.encode(question).tolist()

	# ChromaDB에 저장
	client = chromadb.PersistentClient(path="chroma_db")
	# collection = client.get_or_create_collection(name="questions")

	collection = client.get_or_create_collection(
		name="questions",
		embedding_function=embed_fn,
		metadata={
			# 거리(metric): cosine 또는 ip(inner_product)
			"hnsw:space": "cosine",           # :contentReference[oaicite:0]{index=0}
			# 각 노드가 가질 최대 링크 수 (높을수록 정확↑·메모리↑)
			"hnsw:M": 32,
			# 인덱스 생성 시 탐색 깊이 (높을수록 느리지만 정확↑)
			"hnsw:construction_ef": 200,
			# 검색 시 탐색할 후보 그래프 크기 (trade‑off)
			"hnsw:search_ef": 50
		}
	)

	collection.add(
		embeddings=[embedding],
		ids=[str(question_id)]
	)

	# 결과 반환
	print(json.dumps({"status": "success", "message": "Question saved to vector DB"}))

if __name__ == "__main__":
	main()