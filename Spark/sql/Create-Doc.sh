if ! hash python 2>/dev/null; then
  echo "Missing python in your path, skipping SQL documentation generation."
  exit 0
fi

if ! hash mkdocs 2>/dev/null; then
  echo "Missing mkdocs in your path, trying to install mkdocs for SQL documentation generation."
  pip install mkdocs
fi

pushd "$FWDIR" > /dev/null

# Now create the markdown file
rm -fr docs
mkdir docs
echo "Generating markdown files for SQL documentation."
"$SPARK_HOME/bin/spark-submit" gen-sql-markdown.py

# Now create the HTML files
echo "Generating HTML files for SQL documentation."
mkdocs build --clean
rm -fr docs

popd
