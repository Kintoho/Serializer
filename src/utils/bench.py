import time, os, gzip

from math import ceil

from utils.lz77 import LZ77_Encode
from utils.huffman import Huffman_Encode

def get_compression_time_and_data(data):
    start_time = time.time()

    compressed_data = Huffman_Encode(LZ77_Encode(data))

    end_time = time.time()

    return end_time - start_time, compressed_data

def print_results(method, time, og_size, comp_size):
    print(f"<<{method}>> compression time: {time * 1000} ms")
    print(f"og size: {og_size} bytes")
    print(f"comp size: {comp_size} bytes\n")

    if method == "GZIP":
        print(f"{''.join(['-']*100)}\n")
        

def launch_test(input_file):
    input_file = "src/data/" + input_file
    output_gzip_file = "src/data/compressed_data_gzip.gz"

    with open(input_file, "r", encoding="utf-8") as file:
        data = file.read()

    compression_time, compressed_data = get_compression_time_and_data(data)

    print_results("OUR", compression_time, len(data), ceil(len(compressed_data) / 8))

    start_time = time.time()

    with open(input_file, 'rb') as f_in:
        with gzip.open(output_gzip_file, 'wb') as f_out:
            f_out.writelines(f_in)

    end_time = time.time()

    print_results("GZIP", end_time - start_time, len(data), os.path.getsize(output_gzip_file))