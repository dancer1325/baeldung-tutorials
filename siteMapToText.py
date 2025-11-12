import re
import glob

# Match any local sitemap XML files, e.g. post-sitemap1.xml, post-sitemap2.xml...
xml_files = sorted(glob.glob("post-sitemap*.xml"))

all_urls = []

for filename in xml_files:
    with open(filename, encoding="utf-8") as f:
        data = f.read()

    # Extract URLs inside <loc>...</loc>
    urls = re.findall(r"<loc>(.*?)</loc>", data)

    # Clean CDATA markers and whitespace
    cleaned = [u.replace("<![CDATA[", "").replace("]]>", "").strip() for u in urls]

    print(f"{filename}: {len(cleaned)} URLs")
    all_urls.extend(cleaned)

# Remove duplicates and sort
all_urls = sorted(set(all_urls))

# Save to text file
output_file = "baeldung_articles_list.txt"
with open(output_file, "w", encoding="utf-8") as f:
    for u in all_urls:
        f.write(u + "\n")

print(f"\n✅ {len(all_urls)} total URLs saved in '{output_file}'")
