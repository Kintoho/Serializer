import heapq

class Node:
    def __init__(self, value, freq):
        self.value = value
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, node):
        return self.freq < node.freq

def build_tree(frequency_map):
    heap = [ Node(value, freq) for value, freq in frequency_map.items() ]
    heapq.heapify(heap)

    while len(heap) > 1:
        left = heapq.heappop(heap)
        right = heapq.heappop(heap)

        merged_node = Node(None, left.freq + right.freq)
        merged_node.left = left
        merged_node.right = right

        heapq.heappush(heap, merged_node)

    return heap[0]

def build_codes_map(node, current_code, codes):
    if node.value is not None:
        codes[node.value] = current_code
        return
    if node.left is not None:
        build_codes_map(node.left, current_code + '0', codes)
    if node.right is not None:
        build_codes_map(node.right, current_code + '1', codes)

def Huffman_Encode(data):
    frequency_map = {}
    for key in data:
        frequency_map[key] = frequency_map.get(key, 0) + 1

    root = build_tree(frequency_map)

    codes_map = {}
    build_codes_map(root, '', codes_map)

    compressed_data = ''.join(codes_map[key] for key in data)

    return compressed_data
